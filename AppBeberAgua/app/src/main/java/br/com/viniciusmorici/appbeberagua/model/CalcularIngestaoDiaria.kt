package br.com.viniciusmorici.appbeberagua.model

class CalcularIngestaoDiaria {
//ml x peso
//<= 17 = 40ml
//18 >= 55 = 35ml
//56 >= 65 = 30ml
//>66 = 25ml
    private val mlJovem = 40.0
    private val mlAdulto = 35.0
    private val mlIdoso = 30.0
    private val mlSenil = 25.0

    private var resultadoMl = 0.0
    private var resultadoTotalMl = 0.0

    fun CalculatTotal(peso: Double, idade: Int){
        if (idade <= 17){
            resultadoMl = peso * mlJovem
            resultadoTotalMl = resultadoMl
        }
        else if (idade <= 55){
            resultadoMl = peso * mlAdulto
            resultadoTotalMl = resultadoMl

        }else if (idade <= 65){
            resultadoMl = peso * mlIdoso
            resultadoTotalMl = resultadoMl
        }else if (idade >= 66){
            resultadoMl = peso * mlSenil
            resultadoTotalMl = resultadoMl
        }
    }

    fun ResultadoMl(): Double{
        return resultadoTotalMl
    }


}