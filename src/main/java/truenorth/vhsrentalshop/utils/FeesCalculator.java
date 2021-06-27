package truenorth.vhsrentalshop.utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FeesCalculator {
	
	@Value("${rental.dailyfee}")
	private double dailyFee;
	
	public BigDecimal calculateFee(LocalDateTime dueDate, LocalDateTime dateReturned) {
		long daysLate = ChronoUnit.DAYS.between(dueDate, dateReturned);
		
		return BigDecimal.valueOf(dailyFee * daysLate);
	}

}
