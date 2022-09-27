import java.lang.NumberFormatException

class Juego{
    var tableroJugador = Array(8){Array(8){"~~~"} }
    var tableroIA = Array(8){Array(8){"~~~"} }

    fun pintarTablero(mapa : Array<Array<String>>)
    {
        var letter = 65
        println("-----------------------------------")
        println("  | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |")
        for(i in 0 until 8)
        {
            print("|${letter.toChar()}")
            letter++
            for(x in 0 until 8)
            {
                print("|" + mapa[i][x])
            }
            println("|")
        }
        println("-----------------------------------")
    }
    private fun comprobarBarcos(barcoAColocar : Barco, mapa : Array<Array<String>>) : Boolean{
        var estaOcupado = false
        val coordenadasLetras = mutableListOf<Int>()
        val coordenadasNum = mutableListOf<Int>()

        for(i in 0 until barcoAColocar.tamanoBarco)
        {
            if(barcoAColocar.direccion == 0 && barcoAColocar.coordenadasLetras[0] - i >= 0)
            {
                if(mapa[barcoAColocar.coordenadasLetras[0] - i][barcoAColocar.coordenadasNumeros[0]].compareTo("~~~") !=0)
                {
                    estaOcupado = true
                    break
                }
                else
                {
                    coordenadasLetras.add(barcoAColocar.coordenadasLetras[0] - i)
                    coordenadasNum.add(barcoAColocar.coordenadasNumeros[0])
                }
            }
            else if(barcoAColocar.direccion == 1 && barcoAColocar.coordenadasNumeros[0] + i <=7)
            {
                if(mapa[barcoAColocar.coordenadasLetras[0]][barcoAColocar.coordenadasNumeros[0] + i].compareTo("~~~") != 0)
                {
                    estaOcupado = true
                    break
                }
                else
                {
                    coordenadasLetras.add(barcoAColocar.coordenadasLetras[0])
                    coordenadasNum.add(barcoAColocar.coordenadasNumeros[0] + i)
                }
            }
            else if(barcoAColocar.direccion == 2 && barcoAColocar.coordenadasLetras[0] + i <= 7)
            {
                if(mapa[barcoAColocar.coordenadasLetras[0] + i][barcoAColocar.coordenadasNumeros[0]].compareTo("~~~") != 0)
                {
                    estaOcupado = true
                    break
                }
                else
                {
                    coordenadasLetras.add(barcoAColocar.coordenadasLetras[0] + i)
                    coordenadasNum.add(barcoAColocar.coordenadasNumeros[0])
                }
            }
            else if(barcoAColocar.direccion == 3 && barcoAColocar.coordenadasNumeros[0] - i >= 0)
            {
                if(mapa[barcoAColocar.coordenadasLetras[0]][barcoAColocar.coordenadasNumeros[0] - i].compareTo("~~~") != 0)
                {
                    estaOcupado = true
                    break
                }
                else
                {
                    coordenadasLetras.add(barcoAColocar.coordenadasLetras[0])
                    coordenadasNum.add(barcoAColocar.coordenadasNumeros[0] - 1)
                }
            }
        }
        if(!estaOcupado){
            barcoAColocar.coordenadasLetras = coordenadasLetras
            barcoAColocar.coordenadasNumeros = coordenadasNum
        }
        else{
            barcoAColocar.coordenadasLetras.removeAt(0)
            barcoAColocar.coordenadasNumeros.removeAt(0)
        }

        return estaOcupado
    }

    fun pintarBarco(barco : Barco, mapa : Array<Array<String>>) : Boolean{
        var faltaAlgo = false
        if(!comprobarBarcos(barco, mapa))
        {
            if (barco.nombre.compareTo("Submarino") == 0)
            {
                for (x in 0 until barco.tamanoBarco)
                    mapa[barco.coordenadasLetras[x]][barco.coordenadasNumeros[x]] = " S "

            }
            else if (barco.nombre.compareTo("Destructor") == 0)
            {
                for (x in 0 until barco.tamanoBarco)
                    mapa[barco.coordenadasLetras[x]][barco.coordenadasNumeros[x]] = " D "
            }
            else if (barco.nombre.compareTo("Crucero") == 0)
            {
                for (x in 0 until barco.tamanoBarco)
                    mapa[barco.coordenadasLetras[x]][barco.coordenadasNumeros[x]] = " C "
            }
            else if (barco.nombre.compareTo("Acorazado") == 0)
            {
                for (x in 0 until barco.tamanoBarco)
                    mapa[barco.coordenadasLetras[x]][barco.coordenadasNumeros[x]] = " A "
            }
        }
        else
        {
            faltaAlgo = true
        }

        return faltaAlgo

    }

    fun bienvenida(){
        println("----------------------------------------------------------- \n" +
                "--------------------BIENVENIDO AL JUEGO--------------------\n" +
                "-----------------------------------------------------------")
    }

   fun pedirDatos(barco : Barco)
    {
       var condicion = true
        do{
           try{
               println("¿Dónde quieres colocar el ${barco.nombre}?(La letra)")
               barco.coordenadasLetras.add(comprobarLetra(readln()))
               println("¿Dónde quieres colocar el ${barco.nombre}?(El número)")
               barco.coordenadasNumeros.add(comprobarNumero(readln().toInt()))
               println("¿En qué dirección lo quieres? \n Arriba: W \n Derecha: D \n Abajo: S \n Izquierda: A")
               barco.comprobarDireccion(readln())
               condicion = pintarBarco(barco, tableroJugador)
               if(condicion)
                   println("Ha colocado erroneamente el barco, vuelva a introducir los datos")
           }catch(e : NumberFormatException){
               println("Formato numérico erróneo, volvamos a empezar")
           }
        }while(condicion)
        pintarTablero(tableroJugador)

    }

   /* fun atacar(mapa : Array<Array<String>>)
    {
        var coordenadaLetra : Int
        var coordenadaNum : Int
        var comprobar = true
       do{
           try{
               println("¿A qué coordenada quiere atacar?(La letra)")
               coordenadaLetra = comprobarLetra(readln())
               println("¿A qué coordenada quiere atacar?(El número)")
               coordenadaNum = comprobarNumero(readln().toInt())

               if(mapa[coordenadaLetra][coordenadaNum].compareTo(" B ") == 0){
                   mapa[coordenadaLetra][coordenadaNum] = " T "

               }else{
                   mapa[coordenadaLetra][coordenadaNum] = "***"
               }
               comprobar = false

           }catch(e : NumberFormatException){
               println("El formato del número es erróneo, inténtelo de nuevo")
           }
       }while(comprobar)
    }
*/
}

fun main() {
    val barco1_1 = Barco()
    barco1_1.tamanoBarco = 1
    barco1_1.nombre = "Submarino"
    val barco1_2 = Barco()
    barco1_2.tamanoBarco = 1
    barco1_2.nombre = "Submarino"
    val barco2_1 = Barco()
     barco2_1.tamanoBarco = 2
     barco2_1.nombre = "Destructor"
     /*val barco2_2 = Barco()
     barco2_2.tamanoBarco = 2
     barco2_2.nombre = "Destructor"
     val barco3 = Barco()
     barco3.tamanoBarco = 3
     barco3.nombre = "Crucero"
     val barco4 = Barco()
     barco4.tamanoBarco = 4
     barco4.nombre = "Acorazado"*/
    val tableroJuego = Juego()
    tableroJuego.bienvenida()
    tableroJuego.pintarTablero(tableroJuego.tableroJugador)

    tableroJuego.pedirDatos(barco1_1)
    println("${barco1_1.coordenadasLetras}, ${barco1_1.coordenadasNumeros}")
    tableroJuego.pedirDatos(barco1_2)
    tableroJuego.pedirDatos(barco2_1)
    println("${barco2_1.coordenadasLetras}, ${barco2_1.coordenadasNumeros}")
}

fun comprobarLetra(letra : String) : Int{
    var coordenada = letra.uppercase()
    var coordenadaNum = -1
    do{
        when(coordenada){
            "A" -> coordenadaNum = 0
            "B" -> coordenadaNum = 1
            "C" -> coordenadaNum = 2
            "D" -> coordenadaNum = 3
            "E" -> coordenadaNum = 4
            "F" -> coordenadaNum = 5
            "G" -> coordenadaNum = 6
            "H" -> coordenadaNum = 7
            else ->{
                println("Letra equivocada, inténtelo de nuevo:")
                coordenada = readln().uppercase()
            }
        }
    }while(coordenadaNum == -1)
    return coordenadaNum
}

fun comprobarNumero(num : Int) : Int{
    var coordenada = num
    var coordenadaNumero = -1
    do
    {
        try
        {
            if(coordenada in 1..8)
            {
                coordenadaNumero = coordenada - 1
            }
            else
            {
                println("Número equivocado, inténtelo de nuevo:")
                coordenada = readln().toInt()
            }
        }
        catch(e : NumberFormatException)
        {
            println("Formato numérico incorrecto, inténtalo de nuevo")
            coordenada = readln().toInt()
        }
    }while(coordenadaNumero == -1)
    return coordenadaNumero
}
