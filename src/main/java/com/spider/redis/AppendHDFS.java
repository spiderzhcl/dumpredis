/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spider.redis;

/**
 *
 * @author chunlei
 */
public class AppendHDFS {

    public static void main(String[] args) {
        byte[] aaa = new byte[]{
            85, 0, 0, 0, 66, 0, 0, 0, 
            8, 0, 
            
            0, -64, -32, 8, 4, 17, 
            49, 52, 48, 54, 50, 53, 57, 52, 52, 50, 58, 58, 49, 48, 46, 48, 58, //1406259442::10.0:
            
            19, 3, 
            67, 65, 84, //CAT
            5, -14, 
            
            2, -64, -104, 11, 4, 16, 
            49, 52, 48, 54, 50, 53, 57, 57, 53, 57, 58, 58, 51, 46, 48, 58,  //1406259959::3.0:
            
            18, -64, -1, 13, 4, 16, 
            49, 52, 48, 54, 50, 54, 48, 48, 49, 51, 58, 58, 49, 46, 53, 58, //1406260013::1.5:
            -1};
        
        aaa = new byte[]{40,0,0,0,
            37,0,0,0,
            4,0,
            0,-64,21,1,4,16,49,52,48,54,50,55,52,55,54,54,58,58,54,46,53,58,
            18,3,
            67,65,84,5,-14,-1};
        byte[] buf = new byte[]{-12};
        int type = (buf[0] & 0x00f0) >> 4;
         int value =buf[0]+15;
        System.out.println(type);
        System.out.println(value);
        System.out.println((buf[0] & 0x000f));
    }

}
