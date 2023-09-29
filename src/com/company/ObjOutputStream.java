package com.company;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

//15
class ObjOutputStream extends ObjectOutputStream {
    public ObjOutputStream(OutputStream os) throws IOException {
        super(os);
    }
    @Override
    protected void writeStreamHeader() throws IOException {
        reset();
    }
}