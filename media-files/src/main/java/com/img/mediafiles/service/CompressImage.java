package com.img.mediafiles.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class CompressImage {

    public static byte[] compressImage(byte[] data){
        Deflater deflater=new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream=new ByteArrayOutputStream(data.length);
        try {
            byte[] temp=new byte[4*1024];
            while (!deflater.finished()){
                int size=deflater.deflate(temp);
                outputStream.write(temp,0,size);
            }
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return outputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] data){
        Inflater inflater=new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream(data.length);
        byte[] temp=new byte[4*1024];

            try {
                while (!inflater.finished()) {
                    int size = inflater.inflate(temp);
                    outputStream.write(temp,0,size);
                }
                outputStream.close();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        return outputStream.toByteArray();
    }

}
