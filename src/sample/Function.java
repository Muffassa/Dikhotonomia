package sample;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.ArrayList;

/**
 * Created by muffass on 27.11.15.
 */
public class Function {
    private static Double[] interval = new Double[2];
    private static Double[] resultInterval = new Double[2];
    private static String function;

    public  Double[] getResultInterval() {
        return resultInterval;
    }

    //Method for calculating function(current)
    private static Double f(double x){

        ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");
        Double result = 0.0;
        try

        {
            String functionToCalc = function.toLowerCase();

            functionToCalc = functionToCalc.replace("x", String.valueOf(x)).replace("pow", "Math.pow");
            result = Double.parseDouble(String.valueOf(engine.eval(functionToCalc)));
        } catch (Exception e) {
            throw new ArithmeticException("math error");
        }

        return result;
    }

    public Function(String function)
    {
        this.function = function;
    }

    /*public static void main(String[] args) {
        double result = findMin(1, 1, 0.00001, 0.001);

        System.out.println("" + result);
    }*/

    public static double findMin(double x0, double h,double e, double l) {

        findInterval(x0, h);
        resultInterval[0] = interval[0];
        resultInterval[1] = interval[1];

        int k = 0;

        double y;
        double z;
        double L = Math.abs(interval[1] - interval[0]);

        while (L >= l){
            y = (interval[0] + interval[1] - e) / 2;
            z = (interval[0] + interval[1] + e) / 2;

            if (f(y) <= f(z)) {
                interval[1] = z;
            } else if (f(y) > f(z)) {
                interval[0] = y;
            }
            L = Math.abs(interval[1] - interval[0]);
        }
        double result = ((interval[0] + interval[1]) / 2);

        return result;
        /*System.out.println("x = " + ((interval[0] + interval[1]) / 2));*/
    }


    //function for finding interval by method SWAN
    public static void findInterval(double start, double h)
    {
        ArrayList<Double> x = new ArrayList<>();
        int k = 0;
        double delta;
        x.add(k, start);

        double f1 = f(x.get(k) - h);
        double f2 = f(x.get(k));
        double f3 = f(x.get(k) + h);
        if(f(x.get(k) + h) >= f(x.get(k)) && f(x.get(k)) <= f(x.get(k) - h) )
        {
            interval[0] = x.get(k) - h;
            interval[1] = x.get(k) + h;
        }
        else if(f(x.get(k) - h) <= f(x.get(k)) && f(x.get(k)) >= f(x.get(k) + h))
        {
            System.out.println("Поменяйте x0");
        }
        else
        {
            delta = 0;
            if(f(x.get(k) - h) >= f(x.get(k)) && f(x.get(k)) >= f(x.get(k) + h))
            {
                delta = h;
                interval[0] = start;
                x.add(k+1, x.get(k) + h);
            }
            else if(f(x.get(k) -h) <= f(x.get(k)) && f(x.get(k)) <= f(x.get(k) + h))
            {
                delta = -h;
                interval[1] = start;
                x.add(k+1, x.get(k) - h);
            }
            k++;

            while (true)
            {
                x.add(k+1, x.get(k) + Math.pow(2, k)*delta);

                if(f(x.get(k+1)) < f(x.get(k)) && delta == h)
                {
                    interval[0] = x.get(k);
                }
                else if(f(x.get(k+1)) < f(x.get(k)) && delta == -h)
                {
                    interval[1] = x.get(k);
                }
                k++;
                if((f(x.get(k)) >= f(x.get(k - 1)))) break;
            }


            if(delta == h)
            {
                interval[1] = x.get(k);
            }
            else if(delta == -h)
            {
                interval[0] = x.get(k);
            }
        }

    }
}
