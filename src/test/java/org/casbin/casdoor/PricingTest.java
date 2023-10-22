// Copyright 2023 The Casdoor Authors. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.casbin.casdoor;

import org.casbin.casdoor.entity.Pricing;
import org.casbin.casdoor.service.PricingService;
import org.casbin.casdoor.support.TestDefaultConfig;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class PricingTest {

    private final PricingService pricingService = new PricingService(
            TestDefaultConfig.InitConfig());

    @Test
    public void testPricing() {
        String name = TestDefaultConfig.getRandomName("Pricing");

        // Add a new object
        Pricing pricing = new Pricing(
                "admin",
                name,
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                name,
                "app-admin",
                "Casdoor Website"
        );
        assertDoesNotThrow(() -> pricingService.addPricing(pricing));

        // Get all objects, check if our added object is inside the list
        List<Pricing> pricings;
        try {
            pricings = pricingService.getPricings();
        } catch (Exception e) {
            fail("Failed to get objects: " + e.getMessage());
            return;
        }

        boolean found = pricings.stream().anyMatch(item -> item.name.equals(name));
        assertTrue(found, "Added object not found in list");

        // Get the object
        Pricing retrievedPricing;
        try {
            retrievedPricing = pricingService.getPricing(name);
        } catch (Exception e) {
            fail("Failed to get object: " + e.getMessage());
            return;
        }
        assertEquals(name, retrievedPricing.name, "Retrieved object does not match added object");

        // Update the object
        String updatedDescription = "Updated Casdoor Website";
        retrievedPricing.description = updatedDescription;
        assertDoesNotThrow(() -> pricingService.updatePricing(retrievedPricing));

        // Validate the update
        Pricing updatedPricing;
        try {
            updatedPricing = pricingService.getPricing(name);
        } catch (Exception e) {
            fail("Failed to get updated object: " + e.getMessage());
            return;
        }
        assertEquals(updatedDescription, updatedPricing.description, "Failed to update object, description mismatch");

        // Delete the object
        assertDoesNotThrow(() -> pricingService.deletePricing(retrievedPricing));

        // Validate the deletion
        Pricing deletedPricing;
        try {
            deletedPricing = pricingService.getPricing(name);
        } catch (Exception e) {
            fail("Failed to delete object: " + e.getMessage());
            return;
        }
        assertNull(deletedPricing, "Failed to delete object, it's still retrievable");
    }

}

