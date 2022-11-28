package vn.codingt.clean.rsql.path;

public enum JsonType {

    JSON() {
        @Override
        String getColumnType() {return "json";}
        @Override
        String getExtractPathFunction() {return "json_extract_path_text";}
    },
    JSONB() {
        @Override
        String getColumnType() {return "jsonb";}
        @Override
        String getExtractPathFunction() {return "jsonb_extract_path_text";}
    };


    abstract String getColumnType();
    abstract String getExtractPathFunction();


}
