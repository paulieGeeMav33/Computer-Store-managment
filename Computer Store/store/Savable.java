package store;

import java.io.BufferedWriter;
import java.io.IOException;

public interface Savable {
    public void save(BufferedWriter bw) throws IOException;
}
