package com.example.vending_machine_simulator.controllers;

import com.example.vending_machine_simulator.models.APIResponse;
import com.example.vending_machine_simulator.models.PurchaseAction;
import com.example.vending_machine_simulator.models.vending_machine.SnackVendingMachine;
import com.example.vending_machine_simulator.services.SnacksVendingMachinePurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class is a REST controller that handles the API requests related to a Snacks Vending Machine.
 * It provides endpoints for getting the vending machine details and processing purchase actions.
 */

@RequestMapping("/api/v0/vending-machine")
@RestController
@RequiredArgsConstructor
public class SnacksVendingMachineController {

    private final SnacksVendingMachinePurchaseService purchaseService;

    /**
     * This endpoint returns the details of the Snacks Vending Machine.
     *
     * @return ResponseEntity containing APIResponse object that holds the vending machine details.
     */
    @GetMapping
    public ResponseEntity<APIResponse> getSnackVendingMachine() {
        APIResponse result = new APIResponse(SnackVendingMachine.getInstance());
        return ResponseEntity.ok().body(result);
    }

    /**
     * This endpoint starts the purchase process by creating a new purchase object and returning it to the client.
     *
     * @return ResponseEntity containing APIResponse object that holds the newly created purchase object.
     */
    @GetMapping("/purchase")
    public ResponseEntity<APIResponse> startPurchase() {
        APIResponse result = new APIResponse(purchaseService.startPurchase());
        return ResponseEntity.ok().body(result);
    }

    /**
     * This endpoint processes the purchase action by validating the request and delegating the processing to the purchase service.
     *
     * @param purchaseAction The purchase action object that contains the details of the purchase request.
     * @return ResponseEntity containing APIResponse object that holds the response to the purchase action request.
     * @throws Exception If the purchase action is not valid or cannot be processed.
     */
    @PostMapping("/purchase")
    public ResponseEntity<APIResponse> processPurchaseAction(@RequestBody PurchaseAction purchaseAction) throws Exception {
        // validate
        APIResponse result = new APIResponse(purchaseService.processPurchase(purchaseAction));
        return ResponseEntity.ok().body(result);
    }

}
