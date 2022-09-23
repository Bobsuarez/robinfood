/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.robinfood.task.domain.traslate;

/**
 *
 * @author Bobsuarez
 * @param <inData>
 * @param <outData>
 */
public interface Translator<inData, outData>
{
  outData translate (inData input);
}
