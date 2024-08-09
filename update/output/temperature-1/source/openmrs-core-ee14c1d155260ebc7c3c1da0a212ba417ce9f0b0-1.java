import org.junit.jupiter.api.Test;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DrugOrderValidatorTest {

    @Test
    void validate_shouldFailValidationIfDoseUnitsIsNullForSIMPLEDosingType() throws Exception {
        DrugOrder order = new DrugOrder();
        order.setDosingType(DrugOrder.DosingType.SIMPLE);
        // Assuming we have a way to set up dosing instructions that reflect a SIMPLE type internally
        // This could be part of setting the dosing type, or may need to be explicitly done if the API allows
        // For the sake of this example, we're assuming dosing instructions are set up accordingly
        order.setDoseUnits(null);

        Errors errors = new BindException(order, "order");
        new DrugOrderValidator().validate(order, errors);

        assertTrue(errors.hasFieldErrors("doseUnits"), "Expected validation failure for null doseUnits with SIMPLE dosing type");
    }
}