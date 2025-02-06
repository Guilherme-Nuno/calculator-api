import com.guilherme.calculator.CalculatorProducer;
import com.guilherme.calculator.CalculatorService;
import com.guilherme.common.CalculatorRequest;
import com.guilherme.common.CalculatorResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = CalculatorService.class)
public class CalculatorServiceTest {

    @MockitoBean
    private CalculatorProducer calculatorProducer;

    @Autowired
    private CalculatorService calculatorService;

    private String id;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID().toString();
    }

    @Test
    void testAddition() {
        CalculatorRequest request = new CalculatorRequest("add", new BigDecimal(5), new BigDecimal(3));
        calculatorService.processCalculationRequest(id, request);

        ArgumentCaptor<CalculatorResult> resultArgumentCaptor = ArgumentCaptor.forClass(CalculatorResult.class);

        verify(calculatorProducer).sendCalculationResult(eq(id), resultArgumentCaptor.capture());
        assertEquals(new BigDecimal(8), resultArgumentCaptor.getValue().getResult());
    }

    @Test
    void testSubtraction() {
        CalculatorRequest request = new CalculatorRequest("sub", new BigDecimal(5), new BigDecimal(3));
        calculatorService.processCalculationRequest(id, request);

        ArgumentCaptor<CalculatorResult> resultArgumentCaptor = ArgumentCaptor.forClass(CalculatorResult.class);

        verify(calculatorProducer).sendCalculationResult(eq(id), resultArgumentCaptor.capture());
        assertEquals(new BigDecimal(2), resultArgumentCaptor.getValue().getResult());
    }

    @Test
    void testMultiplication() {
        CalculatorRequest request = new CalculatorRequest("mul", new BigDecimal(5), new BigDecimal(3));
        calculatorService.processCalculationRequest(id, request);

        ArgumentCaptor<CalculatorResult> resultArgumentCaptor = ArgumentCaptor.forClass(CalculatorResult.class);

        verify(calculatorProducer).sendCalculationResult(eq(id), resultArgumentCaptor.capture());
        assertEquals(new BigDecimal(15), resultArgumentCaptor.getValue().getResult());
    }

    @Test
    void testDivision() {
        CalculatorRequest request = new CalculatorRequest("div", new BigDecimal(6), new BigDecimal(3));
        calculatorService.processCalculationRequest(id, request);

        ArgumentCaptor<CalculatorResult> resultArgumentCaptor = ArgumentCaptor.forClass(CalculatorResult.class);

        verify(calculatorProducer).sendCalculationResult(eq(id), resultArgumentCaptor.capture());
        assertEquals(new BigDecimal(2), resultArgumentCaptor.getValue().getResult());
    }

    @Test
    void testDivisionWithDecimals() {
        CalculatorRequest request = new CalculatorRequest("div", new BigDecimal(1), new BigDecimal(4));
        calculatorService.processCalculationRequest(id, request);

        ArgumentCaptor<CalculatorResult> resultArgumentCaptor = ArgumentCaptor.forClass(CalculatorResult.class);

        verify(calculatorProducer).sendCalculationResult(eq(id), resultArgumentCaptor.capture());
        assertEquals(new BigDecimal("0.25"), resultArgumentCaptor.getValue().getResult());
    }
}
