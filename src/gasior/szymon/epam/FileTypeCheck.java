package gasior.szymon.epam;

import gasior.szymon.epam.Exceptions.BadFormatException;

import java.io.File;
import java.io.IOException;

public interface FileTypeCheck {

    boolean isExtensionCorrect() throws BadFormatException;

    String getMimeType();

    FileType getFileTypeDesignatedByMagicNumbers();

    File getFile();

    void setFile(File file) throws IOException;


}
