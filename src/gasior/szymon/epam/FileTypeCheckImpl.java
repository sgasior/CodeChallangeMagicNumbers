package gasior.szymon.epam;

import gasior.szymon.epam.Exceptions.BadFormatException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileTypeCheckImpl implements FileTypeCheck {

    private File file;

    private String mimeType;

    private FileType fileTypeDesignatedByMagicNumbers;


    public FileTypeCheckImpl(File file) throws IOException {
        this.file = file;
        update();
    }


    public boolean isExtensionCorrect() throws BadFormatException {

        switch (this.mimeType) {
            case "image/jpeg":
                if (fileTypeDesignatedByMagicNumbers.equals(FileType.JPGEG)) {
                    return true;
                }
                break;
            case "image/gif":
                if (fileTypeDesignatedByMagicNumbers.equals(FileType.GIF87a) || fileTypeDesignatedByMagicNumbers.equals(FileType.GIF89a)) {
                    return true;
                }
                break;
            case "image/png":
                if (fileTypeDesignatedByMagicNumbers.equals(FileType.PNG)) {
                    return true;
                }
                break;
            case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
                if (fileTypeDesignatedByMagicNumbers.equals(FileType.DOCX)) {
                    return true;
                }
                break;
            case "application/msword":
                if (fileTypeDesignatedByMagicNumbers.equals(FileType.RTF)) {
                    return true;
                }
                break;
            default:
                throw new BadFormatException("Extension is invalid: \n Extension is " + this.fileTypeDesignatedByMagicNumbers.name() + " while actually it's a " + this.mimeType);
        }
        return false;
    }


    private void update() throws IOException {
        setMimeType();
        setFileTypeDesignatedByMagicNumbers();
        isExtensionCorrect();
    }


    private List<String> readInFile(File file) {

        List<String> dataList = null;

        try {

            dataList = new ArrayList<>();

            InputStream inputstream = new FileInputStream(file.getPath());
            int data = inputstream.read();
            while (data != -1) {
                dataList.add(Integer.toHexString(data));
                data = inputstream.read();
            }
            inputstream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList;
    }


    private void setMimeType() throws IOException {
        this.mimeType = Files.probeContentType(Paths.get(file.getPath()));
    }


    private void setFileTypeDesignatedByMagicNumbers() {
        List<String> dataList = readInFile(file);

        for (FileType type : FileType.values()) {
            if (isHexSignatureMatch(type, dataList)) {
                this.fileTypeDesignatedByMagicNumbers = type;
            }

            if (this.fileTypeDesignatedByMagicNumbers == null) {
                this.fileTypeDesignatedByMagicNumbers = FileType.NOT_MATCH;
            }
        }

    }


    private boolean isHexSignatureMatch(FileType fileType, List<String> dataList) {
        boolean result = true;
        String hexCodes[] = fileType.toString().split(" ");
        for (int i = 0; i < hexCodes.length; i++) {
            if (!hexCodes[i].equalsIgnoreCase(dataList.get(i))) {
                return false;
            }
        }
        return result;
    }


    public File getFile() {
        return file;
    }

    public void setFile(File file) throws IOException {
        this.file = file;
        update();
    }

    public String getMimeType() {
        return mimeType;
    }


    public FileType getFileTypeDesignatedByMagicNumbers() {
        return fileTypeDesignatedByMagicNumbers;
    }


}
