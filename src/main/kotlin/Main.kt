import java.lang.NumberFormatException

class Juego{
    var tableroJugador = Array(8){Array(8){"~~~"} }
    var tableroIA = Array(8){Array(8){"~~~"} }

    fun pintarTablero(){
        var letter = 65
        println("-----------------------------------")
        println("  | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |")
        for(i in 0 until 8){
            print("|${letter.toChar()}")
            letter++
            for(x in 0 until 8){
                print("|" + tableroJugador[i][x])
            }
            println("|")
        }
        println("-----------------------------------")
    }
    private fun comprobarBarcos(barcoAColocar : Barco, mapa : Array<Array<String>>) : Boolean{
        var estaOcupado = false
        for(i in 0 until barcoAColocar.tamanoBarco){
            if(barcoAColocar.direccion == 0 && barcoAColocar.coordenadaLetra - i >=0){
                if(mapa[barcoAColocar.coordenadaLetra - i][barcoAColocar.coordenadaNumero].compareTo(" B ") == 0){
                    estaOcupado = true
                    break
                }
            }else if(barcoAColocar.direccion == 1 && barcoAColocar.coordenadaNumero + i <= 7){
                if(mapa[barcoAColocar.coordenadaLetra][barcoAColocar.coordenadaNumero + i].compareTo(" B ") == 0){
                    estaOcupado = true
                    break
                }
            }else if(barcoAColocar.direccion == 2 && barcoAColocar.coordenadaLetra + i <= 7){
                if(mapa[barcoAColocar.coordenadaLetra + i][barcoAColocar.coordenadaNumero].compareTo(" B ") == 0){
                    estaOcupado = true
                    break
                }
            }else if(barcoAColocar.direccion == 3 && barcoAColocar.coordenadaNumero - i >= 0){
                if(mapa[barcoAColocar.coordenadaLetra][barcoAColocar.coordenadaNumero - i].compareTo(" B ") == 0){
                    estaOcupado = true
                    break
                }
            }
        }
        return estaOcupado
    }

    fun pintarBarco(barco : Barco, mapa : Array<Array<String>>) : Boolean{
        var faltaAlgo = false
        if(!comprobarBarcos(barco, mapa))
        {
            if (barco.direccion == 0)
            {
                if (barco.coordenadaLetra - barco.tamanoBarco >= -1)
                {
                    for (x in 0 until barco.tamanoBarco)
                        mapa[barco.coordenadaLetra - x][barco.coordenadaNumero] = " B "
                }

                else
                {
                    faltaAlgo = true
                }
            }
            else if (barco.direccion == 1)
            {
                if (barco.coordenadaNumero + barco.tamanoBarco <= 8)
                {
                    for (x in 0 until barco.tamanoBarco)
                        mapa[barco.coordenadaLetra][barco.coordenadaNumero + x] = " B "
                } else
                {
                    faltaAlgo = true
                }
            }
            else if (barco.direccion == 2)
            {
                if (barco.coordenadaLetra + barco.tamanoBarco <= 8)
                {
                    for (x in 0 until barco.tamanoBarco)
                        mapa[barco.coordenadaLetra + x][barco.coordenadaNumero] = " B "
                }
                else
                {
                    faltaAlgo = true
                }
            }
            else if (barco.direccion == 3)
            {
                if (barco.coordenadaNumero - barco.tamanoBarco >= -1)
                {
                    for (x in 0 until barco.tamanoBarco)
                        mapa[barco.coordenadaLetra][barco.coordenadaNumero - x] = " B "
                }
                else
                {
                    faltaAlgo = true
                }
            }
            else
            {
                faltaAlgo = true

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
               barco.comprobarLetra(readln())
               println("¿Dónde quieres colocar el ${barco.nombre}?(El número)")
               barco.comprobarNumero(readln().toInt())
               println("¿En qué dirección lo quieres? \n Arriba: W \n Derecha: D \n Abajo: S \n Izquierda: A")
               barco.comprobarDireccion(readln())
               condicion = pintarBarco(barco, tableroJugador)
               if(condicion)
                   println("Ha colocado erroneamente el barco, vuelva a introducir los datos")
           }catch(e : NumberFormatException){
               println("Formato numérico erróneo, volvamos a empezar")
           }
        }while(condicion)
        pintarTablero()

    }

    fun atacar(mapa : Array<Array<String>>)
    {
        var coordenadaLetra : Int
        var coordenadaNum : Int
        var comprobar = true
       do{
           try{
               println("¿A qué coordenada quiere atacar?(La letra)")
               coordenadaLetra = readln().toInt() - 1
               println("¿A qué coordenada quiere atacar?(El número)")
               coordenadaNum = readln().toInt() - 1

               if(coordenadaLetra in 0..7 && coordenadaNum in 0..7)
               {
                   if(mapa[coordenadaLetra][coordenadaNum].compareTo(" B ") == 0){
                       mapa[coordenadaLetra][coordenadaNum] = " T "

                   }else{
                       mapa[coordenadaLetra][coordenadaNum] = "***"
                   }
                   comprobar = false
               }
               else
               {
                   println("Las coordenadas son erróneas, inténtelo de nuevo")
               }
           }catch(e : NumberFormatException){
               println("El formato del número es erróneo, inténtelo de nuevo")
           }


       }while(comprobar)
    }

}

fun main(args: Array<String>) {
    val barco1_1 = Barco()
    barco1_1.tamanoBarco = 1
    barco1_1.nombre = "Submarino"
    val barco1_2 = Barco()
    barco1_2.tamanoBarco = 1
    barco1_2.nombre = "Submarino"
    /* val barco2_1 = Barco()
     barco2_1.tamanoBarco = 2
     barco2_1.nombre = "Destructor"
     val barco2_2 = Barco()
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
    tableroJuego.pintarTablero()

    tableroJuego.pedirDatos(barco1_1);
//    println("${barco1_1.coordenadaLetra}, ${barco1_1.direccion}")
    tableroJuego.pintarBarco(barco1_1, tableroJuego.tableroJugador)
    tableroJuego.pedirDatos(barco1_2)
    tableroJuego.pintarBarco(barco1_2, tableroJuego.tableroJugador)
    tableroJuego.pintarTablero()
    tableroJuego.atacar(tableroJuego.tableroJugador)
    tableroJuego.pintarTablero()

}