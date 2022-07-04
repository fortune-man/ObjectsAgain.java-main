package movieTicketing;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

public class Movie {
    private String title;
    private Duration runningTime;
    private Money fee;
    private DiscountPolicy discountPolicy;

    Movie avatar = new Movie("아바타",
            Duration.ofMinutes(120),
            Money.wons(10000),
            new PercentDiscountPolicy());

    Movie titanic = new Movie("타이타닉",
            Duration.ofMinutes(180),
            Money.wons(11000),
            new PercentDiscountPolicy(0.1,
                    new PeriodCondition(DayOfWeek.TUESDAY, LocalTime.of(14,0), LocalTime.of(16, 59)),
                    new SequenceCondition(2),
                    new PeriodCondition(DayOfWeek.THURSDAY, LocalTime.of(10,0), LocalTime.of(13,59))));


    public Movie(String title, Duration runningTime, Money fee, DiscountPolicy discountPolicy) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountPolicy = discountPolicy;
    }

    public Money getFee() {
        return fee;
    }

    public Money calculateMovieFee(Screening screening){
        return fee.minus(discountPolicy.calculateDiscountAmount(screening));
    }
}
