package br.nando.Calculadora;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class MathController {

    @RequestMapping(value = "/soma/{n1}/{n2}", method=RequestMethod.GET) //Deixa explícito o uso do método GET
    public double soma(
            @PathVariable String n1,
            @PathVariable String n2) throws Exception {
        
        if((!isNumeric(n1)) || ((!isNumeric(n2)))) {
            throw new Exception();
        }
        return (convertToDouble(n1) + convertToDouble(n2));
    }

    @RequestMapping(value = "/subtracao/{n1}/{n2}", method=RequestMethod.GET)
    public double subtracao(
            @PathVariable String n1,
            @PathVariable String n2) throws Exception {
        
        if((!isNumeric(n1)) || ((!isNumeric(n2)))) {
            throw new Exception();
        }
        return (convertToDouble(n1) - convertToDouble(n2));
    }

    @RequestMapping(value = "/multiplicacao/{n1}/{n2}", method=RequestMethod.GET)
    public double multiplicacao(
            @PathVariable String n1,
            @PathVariable String n2) throws Exception {
        
        if((!isNumeric(n1)) || ((!isNumeric(n2)))) {
            throw new Exception();
        }
        return (convertToDouble(n1) * convertToDouble(n2));
    }

    @RequestMapping(value = "/divisao/{n1}/{n2}", method=RequestMethod.GET)
    public double divisao(
            @PathVariable String n1,
            @PathVariable String n2) throws Exception {
        
        if((!isNumeric(n1)) || ((!isNumeric(n2)))) {
            throw new Exception();
        }
        double divisor = convertToDouble(n2);
        if(divisor == 0) {
            throw new Exception("Divisão por zero não permitida");
        }
        return (convertToDouble(n1) / divisor);
    }

    @RequestMapping(value = "/raizQuadrada/{n1}", method=RequestMethod.GET)
    public double raizQuadrada(
            @PathVariable String n1) throws Exception {
        
        if(!isNumeric(n1)) {
            throw new Exception();
        }
        double numero = convertToDouble(n1);
        if(numero < 0) {
            throw new Exception("Não é possível calcular raiz quadrada de número negativo");
        }
        return Math.sqrt(numero);
    }

    @RequestMapping(value = "/raizCubica/{n1}", method=RequestMethod.GET)
    public double raizCubica(
            @PathVariable String n1) throws Exception {
        
        if(!isNumeric(n1)) {
            throw new Exception();
        }
        double numero = convertToDouble(n1);
        return Math.cbrt(numero);
    }

    @RequestMapping(value = "/potencia/{base}/{expoente}", method=RequestMethod.GET)
    public double potencia(
            @PathVariable String base,
            @PathVariable String expoente) throws Exception {
        
        if((!isNumeric(base)) || ((!isNumeric(expoente)))) {
            throw new Exception();
        }
        return Math.pow(convertToDouble(base), convertToDouble(expoente));
    }

    @RequestMapping(value = "/binario/{n1}", method=RequestMethod.GET)
    public String binario(
            @PathVariable String n1) throws Exception {
        
        if(!isNumeric(n1)) {
            throw new Exception();
        }
        int numero = (int)convertToDouble(n1);
        return Integer.toBinaryString(numero);
    }

    @RequestMapping(value = "/potencia10/{expoente}", method=RequestMethod.GET)
    public double potencia10(
            @PathVariable String expoente) throws Exception {
        
        if(!isNumeric(expoente)) {
            throw new Exception();
        }
        return Math.pow(10, convertToDouble(expoente));
    }

    @RequestMapping(value = "/fatorial/{n1}", method=RequestMethod.GET)
    public long fatorial(
            @PathVariable String n1) throws Exception {
        
        if(!isNumeric(n1)) {
            throw new Exception();
        }
        int numero = (int)convertToDouble(n1);
        if(numero < 0) {
            throw new Exception("Fatorial não é definido para números negativos");
        }
        if(numero == 0 || numero == 1) {
            return 1;
        }
        long resultado = 1;
        for(int i = 2; i <= numero; i++) {
            resultado *= i;
        }
        return resultado;
    }

    @RequestMapping(value = "/pi", method=RequestMethod.GET)
    public double pi() {
        return Math.PI;
    }

    private double convertToDouble(String strNumber) throws Exception {
        if(strNumber == null) throw new Exception();
        
        String number = strNumber.replaceAll(",","."); //troca “,” por “.”
        
        if(isNumeric(number)) {
            return (Double.parseDouble(number));
        } else {
            throw new Exception();
        }
    }

    private boolean isNumeric(String strNumber) {
        if(strNumber == null) {
            return false;
        }
        String number = strNumber.replaceAll(",",".");
        return (number.matches("[-+]?[0-9]*\\.?[0-9]+")); //verifica se uma string corresponde ao padrão de um número decimal
    }
}