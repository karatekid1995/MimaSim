package com.example.mimasim.Simulator

/**
 * Created by Martin on 08.09.2017.
 */
class Register(name: String, short : String,  description : String, content : Int = 0x0) : Element(name, short,  description){

    var Content : Int = content
        set(value) {
            while (value > 0xFFFFFFFF) {
                value.ushr(1)
            }
            field = value
        }

    /*unsinged max hex is 7fffffff*/
    fun setContent(hexString : String){
        var contentString = hexString
        //if Bigger then max length set to max value
        if (hexString.length > 8) {
            contentString = "0xffffffff"
        }
        if(hexString.length == 8) {
            //if first hexcode bigger then 7
            if (hexString[0].toInt() > 7){
                contentString = "0x" + hexString.subSequence(1, 8).toString()
                Content = Integer.decode(contentString)
                val firstHex = hexString[0].toString()
                val firstHexAsInt = Integer.decode("0x" + firstHex)
                Content = Content xor firstHexAsInt.shl(28)
                return
            }
        }
        Content = Integer.decode(contentString)
    }
}