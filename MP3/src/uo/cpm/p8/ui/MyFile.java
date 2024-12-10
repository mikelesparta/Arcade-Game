package uo.cpm.p8.ui;

import java.io.File;

public class MyFile {

    private File f = null;

    public MyFile(File f) {
	this.f = f;
    }

    public File getFile() {
	return f;
    }

    @Override
    public String toString() {
	// Substring in order to remove ".mp3" from the name
	return f.getName().substring(0, f.getName().length() - 4);
    }
}
