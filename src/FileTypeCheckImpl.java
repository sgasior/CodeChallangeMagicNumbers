import gasior.szymon.epam.FileType;
import gasior.szymon.epam.FileTypeCheck;

import java.io.File;

public class FileTypeCheckImpl implements FileTypeCheck {

    private File file;

    private String mimeType;

    private FileType fileTypeDesignatedByMagicNumbers;


    public FileTypeCheckImpl(File file) {
        this.file = file;
    }

    @Override
    public boolean isExtensionCorrect() {
        return false;
    }

    @Override
    public String getMimeType() {
        return null;
    }

    @Override
    public FileType getFileTypeDesignatedByMagicNumbers() {
        return null;
    }

    @Override
    public File getFile() {
        return null;
    }

    @Override
    public void setFile(File file) {

    }
}
