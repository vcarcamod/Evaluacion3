/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.aiep.evaluacion3.utils;

import cl.aiep.evaluacion3.models.Estado;

/**
 *
 * @author exvicad
 */
public class Utils {
    public static int getEstado(Estado e){
        switch(e){
            case PENDIENTE: return 0;
            case ACEPTADO: return 1;
            case RECHAZADO: return 2;
            case ENTREGADO: return 3;
            default: return -1;
        }
    }
    
    public static Estado getEstado(int e){
        switch(e){
            case 0: return Estado.PENDIENTE;
            case 1: return Estado.ACEPTADO;
            case 2: return Estado.RECHAZADO;
            case 3: return Estado.ENTREGADO;
            default: return null;
        }
    }
}
