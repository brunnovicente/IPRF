package iprf;

/**
 *
 * @author brunn
 */
public class Receita {
    //Teto 2018: 5832.11
    private final double salario = 998.0;
    private final double teto = 5839.45;
    
    public double calcular_liquido(double bruto){
        double base = this.calcular_base_calculo(bruto);
        double iprf = this.calcular_iprf(base);
        double liquido = base - iprf;
        return liquido;
    }
    
    public double calcular_funpresp(double salario){
        if(salario >= teto){
            double base = salario - teto;
            return base * 0.085;
        }
        return 0;
    }
    
    public double calcular_base_calculo(double salario){
        double prev = this.calcular_previdencia(salario);
        double base = salario - prev;
        return base;
    }
    
    public double calcular_iprf(double base, double prev){
        base = base - prev;
        if(base <= 1903.98){
            System.out.println("Sem imposto!");
            return 0;
        }else if(base <= 2826.65){
            System.out.println("Imposto 7,5%");
            return (base * 0.075) - 142.8;
        }else if(base <= 3751.05){
            System.out.println("Imposto 15%");
            return (base * 0.225) - 354.8; 
        }else if(base <= 4664.68){
            System.out.println("Imposto 22,5%");
            return (base * 0.225) - 636.13;
        }else{
            System.out.println("Imposto 27,5%");
            return (base*0.275) - 869.36;
        }
    }
    
    public double calcular_iprf_corrigido(double base, double prev){
        System.out.println("IMPOSTO CORRIGIDO!");
        base = base - prev;
        double iprf = 0;
        if (base > 4853.13){
            double faixa = base - 4853.13;
            iprf = faixa * 0.275;
            base -= faixa;
            System.out.println("FAIXA: "+faixa+" - BASE: "+base+ " - "+iprf);
        }
        if(base > 3902.59){
            double faixa = base - 3902.59;
            iprf += faixa * 0.225;
            base -= faixa;
            System.out.println("FAIXA: "+faixa+" - BASE: "+base+ " - "+iprf);
        }
        if(base > 2940.86){
            double faixa = base - 2940.86;
            iprf += faixa * 0.15;
            base -= faixa;
            System.out.println("FAIXA: "+faixa+" - BASE: "+base+ " - "+iprf);
        }
        if(base > 1980.91){
            double faixa = base - 1980.91;
            iprf += faixa * 0.075;
            base -= faixa;
            System.out.println("FAIXA: "+faixa+" - BASE: "+base+ " - "+iprf);
        }
        
        return iprf;
    }
    
    
    public double calcular_iprf_novo(double base, double prev){
        double isensao = salario * 5.0;
        base = base - prev;
        if(base <= isensao){
            return base;
        }else{
            double ir = (base - isensao) * 0.2;
            return ir;
        }
    }
    
    public double calcular_iprf(double base){
        if(base <= 1903.98){
            return 0;
        }else if(base <= 2826.65){
            return (base * 0.075) - 142.8;
        }else if(base <= 3751.05){
            return (base * 0.225) - 354.8; 
        }else if(base <= 4664.68){
            return (base * 0.225) - 636.13;
        }else{
            return (base*0.275) - 869.36;
        }
    }
    
    public double calcular_previdencia(double salario){
        if(salario <= 1751.81){
            System.out.println("Previdencia 8%");
            return salario * 0.08;
        }else if(salario <= 2919.72){
            System.out.println("Previdencia 9%");
            return salario * 0.09;
        }else if(salario <= 5839.45){
            System.out.println("Previdencia 11%");
            return salario * 0.11;
        }else{
            System.out.println("Previdência Máximo 608,44");
            return (this.teto * 0.11);
        }
    }//Fim do cálculo da previdência
    
}//Fim da Classe
