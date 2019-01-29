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


    /**
     * This method is used to check if inputed file has correct extension by comparing mimeType and file type checked by 'magic numbers'
     *
     * @return true format is correct
     * @throws BadFormatException when format is incorrect
     */
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

    /**
     * This method is reading byte of data from the input stream in order to create String list
     *
     * @param file
     * @return file data String List (converted to hexidecimal format)
     */
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

    /**
     * This method is setting the mimeType property to the value of a
     * MIME content type
     *
     * @throws IOException
     */
    private void setMimeType() throws IOException {
        this.mimeType = Files.probeContentType(Paths.get(file.getPath()));
    }


    /**
     * This method is setting the fileTypeDesignatedByMagicNumbers property of this object designated by magic numbers
     * The method specifies which of the magic numbers matches the type of file. It's trying to match hexdecimal numbers from the file
     * to the hexdecimal numbers found in file
     */
    private void setFileTypeDesignatedByMagicNumbers() {
        List<String> dataList = readInFile(file);

        for (FileType type : FileType.values()) {
            if (isHexSignatureMatch(type, dataList)) {
                this.fileTypeDesignatedByMagicNumbers = type;
            }

            // if the file type is not supported then we assign NOT_MATCH
            if (this.fileTypeDesignatedByMagicNumbers == null) {
                this.fileTypeDesignatedByMagicNumbers = FileType.NOT_MATCH;
            }
        }

    }

    /**
     * It's helper method to the setFileTypeDesignatedByMagicNumbers() to check if hexSignature 'magic number' match hexSignature from file
     *
     * @param fileType
     * @param dataList
     * @return true if hexSignature from FileType is same as signagure found in file
     */
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
