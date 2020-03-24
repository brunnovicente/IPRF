package iprf;

/**
 *
 * @author brunn
 */
public class Receita {
    //Teto 2018: 5832.11
    //private final double salario = 998.0;
    public static final double TETO = 6101.06;
    public static final double ALIMENTACAO = 458;
            
    public double calcular_funpresp(double salario){
        if(salario >= TETO){
            double base = salario - TETO;
            return base * 0.085;
        }
        return 0;
    }
    
    public double calculo_base(double salario){
        double prev = this.previdencia(salario);
        double base = salario - prev;
        return base;
    }
             
        
    public double iprf(double base){
        double imposto = 0;
        if(base <= 1903.98){
            imposto = 0;
        }else if(base <= 2826.65){
            imposto = (base * 0.075) - 142.8;
        }else if(base <= 3751.05){
            imposto = (base * 0.225) - 354.8; 
        }else if(base <= 4664.68){
            imposto = (base * 0.225) - 636.13;
        }else{
            imposto = (base*0.275) - 869.36;
        }
        return this.truncar(imposto, 2);
    }
    
    public double previdencia(double salario){
 
        double[] valor = new double[]{1045, 1044.6, 1044.8, 2966.66};//, 4346.94, 10448, 19851.2};
        double[] porcentagem = new double[]{0.075, 0.09, 0.12, 0.14};//, 0.145, 0.165, 0.19};
        double imposto = 0;
 
        for(int i=0;i<4;i++){
            if(salario > valor[i]){
                salario = salario - valor[i];
                imposto += valor[i] * porcentagem[i];
            }else{
                imposto += salario * porcentagem[i];
                break;
            }
        }//Fim do for
        
        //if(salario > 0){
        //    imposto += salario * 0.22;
        //  }
        return this.truncar(imposto, 2);
    }//Fim da Previdência
    
    @Deprecated
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
            return (this.TETO * 0.11);
        }
    }//Fim do cálculo da previdência
    
    public double auxilio_saude(int idade, double salario){
        double[][] dados = new double[][]{
            {149.52, 156.57, 158.69, 165.04, 169.97, 175.61, 190.03, 193.05, 196.06, 205.63},
            {142.47, 149.52, 151.64, 156.57, 161.51, 167.15, 180.76, 183.63, 186.50, 196.06},
            {135.42, 142.47, 144.59, 149.52, 154.46, 160.10, 171.49, 174.21, 176.94, 186.50},
            {129.78, 135.42, 137.53, 142.47, 147.41, 153.05, 163.77, 166.37, 168.97, 176.94},
            {122.71, 129.78, 131.89, 135.42, 140.35, 146.00, 156.04, 158.52, 161.00, 168.97},
            {111.43, 114.25, 116.38, 117.07, 122.02, 127.66, 129.78, 131.84, 133.90, 137.09},
            {107.20, 108.61, 110.73, 111.43, 116.38, 122.02, 123.60, 125.56, 127.52, 130.71},
            {101.56, 102.97, 105.08, 105.79, 110.73, 116.38, 117.42, 119.28, 121.14, 124.33}
                };
        int b = calculo_x(idade);
        int a = calculo_y(salario);

        double auxilio = dados[a][b];
        return auxilio;
    }
    
    private int calculo_x(double idade){
        if(idade <= 18){
            return 0;
        }else if (idade > 18 && idade <= 23){
            return 1;
        }else if(idade >= 24 && idade <= 28){
            return 2;
        }else if(idade >= 29 && idade <= 33){
            return 3;
        }else if(idade >= 34 && idade <= 38){
            return 4;
        }else if(idade >= 39 && idade <= 43){
            return 5;
        }else if(idade >= 44 && idade <= 48){
            return 6;
        }else if(idade >= 49 && idade <= 53){
            return 7;
        }else if(idade >= 54 && idade <= 58){
            return 8;
        }else{
            return 9;
        }
    }

    private int calculo_y(double salario){
        if (salario <= 1499){
            return 0;
        }else if(salario >= 1500 && salario <= 1999){
            return 1;
        }else if(salario >= 2000 && salario <= 2499){
            return 2;
        }else if(salario >= 2500 && salario <= 2999){
            return 3;
        }else if(salario >= 3000 && salario <= 3999){
            return 4;
        }else if(salario >= 4000 && salario <= 5499){
            return 5;
        }else if(salario >= 5500 && salario <= 7499){
            return 6;
        }else{
            return 7;
        }
    }
    
    private double truncar(double d, int casas_decimais) {

        int var1 = (int) d;   // Remove a parte decimal do número... 2.3777 fica 2
        double var2 = var1*Math.pow(10,casas_decimais); // adiciona zeros..2.0 fica 200.0
        double var3 = (d - var1)*Math.pow(10,casas_decimais); /** Primeiro retira a parte decimal fazendo 2.3777 - 2 ..fica 0.3777, depois multiplica por 10^(casas decimais)
        por exemplo se o número de casas decimais que queres considerar for 2, então fica 0.3777*10^2 = 37.77 **/
        int var4 = (int) var3; // Remove a parte decimal da var3, ficando 37
        int var5 = (int) var2; // Só para não haver erros de precisão: 200.0 passa a 200
        int resultado = var5+var4; // O resultado será 200+37 = 237
        double resultado_final = resultado/Math.pow(10,casas_decimais); // Finalmente divide-se o resultado pelo número de casas decimais, 237/100 = 2.37
        return resultado_final; // Retorna o resultado_final :P 
        
    }
    
}//Fim da Classe
