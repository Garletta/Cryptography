```java
package Action;

import DAO.BookDao;
import Table.TBook;
import com.opensymphony.xwork2.ActionContext;

import java.util.ArrayList;
import java.util.Map;

public class queryBookByKeyWordAction {

    private String keyWord;
    private BookDao bookDao;

    public String execute() {
        ArrayList<TBook> list = bookDao.queryBookByKeyWord(keyWord);
        Map session = ActionContext.getContext().getSession();
        session.put("list",list);
        return "success";
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public BookDao getBookDao() {
        return bookDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}

```

