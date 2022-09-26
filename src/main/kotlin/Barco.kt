class Barco()
{
    var tamanoBarco  = 1
         get() = field
         set(tamanyo : Int){
         field = tamanyo
        }

    var coordenadaLetra = -1
        get() = field
        set(coordenada : Int){
            field = coordenada
        }

    fun comprobarLetra(letra : String){
        var coordenada = letra.uppercase()
        do{
            when(coordenada){
                "A" -> coordenadaLetra = 0
                "B" -> coordenadaLetra = 1
                "C" -> coordenadaLetra = 2
                "D" -> coordenadaLetra = 3
                "E" -> coordenadaLetra = 4
                "F" -> coordenadaLetra = 5
                "G" -> coordenadaLetra = 6
                "H" -> coordenadaLetra = 7
                else ->{
                    println("Letra equivocada, inténtelo de nuevo:")
                    coordenada = readln().uppercase()
                }
            }
        }while(coordenadaLetra == -1)
    }

    var coordenadaNumero = -1
        get() = field
        set(coordenada: Int){
                field = coordenada
        }

    fun comprobarNumero(num : Int){
        var coordenada = num
        do{
            try{
                if(coordenada in 1..8){
                    coordenadaNumero = coordenada - 1
                }else{
                    println("Número equivocado, inténtelo de nuevo:")
                    coordenada = readln().toInt()
                }
            }catch(e : NumberFormatException){
                println("Formato numérico incorrecto, inténtalo de nuevo")
                coordenada = readln().toInt()
            }
        }while(coordenadaNumero == -1)
    }

    var direccion = -1
        get() = field
        set(direc : Int){
            field = direc
        }

    fun comprobarDireccion(direc : String) {
        var direccionString = direc.uppercase()
        do{
            when(direccionString){
                "W" -> direccion = 0
                "D" -> direccion = 1
                "S" -> direccion = 2
                "A" -> direccion = 3
                else -> {
                    println("No es un dirección correcta, vuelve a intentarlo")
                    direccionString = readln();
                }
            }
        }while(direccion == -1)
    }

    var nombre = ""


}
