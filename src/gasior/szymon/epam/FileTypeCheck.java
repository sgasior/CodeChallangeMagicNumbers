package gasior.szymon.epam;

import java.io.File;

public interface FileTypeCheck {

    boolean isExtensionCorrect();

    String getMimeType();

    FileType getFileTypeDesignatedByMagicNumbers();

    File getFile();

    void setFile(File file);


}
