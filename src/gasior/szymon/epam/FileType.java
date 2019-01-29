package gasior.szymon.epam;

public enum FileType {

    JPGEG("ff d8"),
    GIF89a("47 49 46 38 39 61"),
    GIF87a("47 49 46 38 37 61"),
    PNG("89 50 4E 47 D A 1A A"),
    DOCX("50 4b 3 4"),
    RTF("7b 5c 72 74 66 31"),
    NOT_MATCH("not match");

    private String hexSignature;

    private FileType(String hexSignature) {
        this.hexSignature = hexSignature;
    }

    @Override
    public String toString() {
        return this.hexSignature;
    }
}


