package empresaTelefonia;

import java.time.LocalDate;
import java.time.Period;

public class Main {
    public static void main(String[] args){
        System.out.println(Period.between(LocalDate.now(), LocalDate.now().plusYears(7).plusMonths(4).plusDays(30)));
    }
}
