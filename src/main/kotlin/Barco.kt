class Barco
{

    var coordenadasLetras = mutableListOf<Int>()
    var coordenadasNumeros = mutableListOf<Int>()
    var tamanoBarco = 0
    var direccion = -1


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
                    direccionString = readln().uppercase()
                }
            }
        }while(direccion == -1)
    }

    var nombre = ""

    var vidas : Int = tamanoBarco
}
