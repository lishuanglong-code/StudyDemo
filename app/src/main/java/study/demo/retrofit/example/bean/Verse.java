package study.demo.retrofit.example.bean;

public class Verse {

    public int code;
    public String message;
    public Result result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }


    public static class Result {
        public String title;
        public String content;
        public String authors;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAuthors() {
            return authors;
        }

        public void setAuthors(String authors) {
            this.authors = authors;
        }
    }
}
