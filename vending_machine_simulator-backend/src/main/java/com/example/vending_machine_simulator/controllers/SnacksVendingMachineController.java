package com.example.vending_machine_simulator.controllers;

import com.example.vending_machine_simulator.models.APIResponse;
import com.example.vending_machine_simulator.models.PurchaseAction;
import com.example.vending_machine_simulator.models.vending_machine.SnackVendingMachine;
import com.example.vending_machine_simulator.services.SnacksVendingMachinePurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v0/vending-machine")
@RestController
@RequiredArgsConstructor
public class SnacksVendingMachineController {

    private final SnacksVendingMachinePurchaseService purchaseService;

    @GetMapping
    public ResponseEntity<APIResponse> getSnackVendingMachine() {
        APIResponse result = new APIResponse(SnackVendingMachine.getInstance());
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/purchase")
    public ResponseEntity<APIResponse> startPurchase() {
        APIResponse result = new APIResponse(purchaseService.startPurchase());
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/purchase")
    public ResponseEntity<APIResponse> processPurchaseAction(@RequestBody PurchaseAction purchaseAction) throws Exception {
        // validate
        APIResponse result = new APIResponse(purchaseService.processPurchase(purchaseAction));
        return ResponseEntity.ok().body(result);
    }

}
