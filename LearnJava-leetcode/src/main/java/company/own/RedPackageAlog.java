package company.own;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RedPackageAlog {
    //    红包算法，给定一个红包总金额和分红包的人数，输出每个人随机抢到的红包数量。
    //    要求：
    //
    //    每个人都要抢到红包，并且金额随机
    //            每个人抢到的金额数不小于1
    //    每个人抢到的金额数不超过总金额的30%
    //    例如总金额100，人数10，输出【19 20 15 1 25 14 2 2 1 1】


    /**
     * 一提到Java里面的商业计算，我们都知道不能用float和double，因为他们无法 进行精确计算。
     * 但是Java的设计者给编程人员提供了一个很有用的类BigDecimal，他可以完善float和double类无法进行精确计算的缺憾。 如果是不需要精确到后两位的话则可以不需要
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Double> ret = new ArrayList<>();
        while (scanner.hasNext()) {
            double totalMoney = scanner.nextDouble();
            double reaminMoney = totalMoney;
//            BigDecimal totalMoneyBD = new BigDecimal(Double.toString(totalMoney));
            int count = scanner.nextInt();
            double money;
            Random random = new Random();
            System.out.println(totalMoney);
            System.out.println(count);
            while (count > 1) {
                double max = Math.min(totalMoney * 0.3, reaminMoney);
                double min = Math.max(reaminMoney - (totalMoney * 0.3 * (count - 1)), 1);
                double doub = random.nextDouble();
                money = Math.floor(max * doub * 100) / 100;
                if (money < min) {
                    money = min;
                }else if (money > max) {
                    money = max;
                }
                ret.add(money);
                reaminMoney -= money;
                count --;
            }
            ret.add(reaminMoney);
            System.out.println(ret);
        }
        scanner.close();
    }
}
