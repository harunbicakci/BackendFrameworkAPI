package Utils;

public class APIConstants {

    public static final String BASE_URI = "https://bookstore.toolsqa.com";

    public static final String CREATE_NEW_ACCOUNT_ENDPOINT = "Account/v1/User";

    public static final String GENERATE_TOKEN_ENDPOINT = "Account/v1/User/{UUID}";

    public static final String ADD_LIST_OF_BOOKS_ENDPOINT = "/BookStore/v1/Books";

    public static final String DELETE_ALL_BOOKS_ENDPOINT = "/BookStore/v1/Books";

    public static final String UPDATE_ONE_BOOK_ENDPOINT = "/BookStore/v1/Books/{ISBN}";

    public static final String GET_ONE_BOOK_ENDPOINT = "/Bookstore/v1/Book";

    public static final String DELETE_ACCOUNT_ENDPOINT = "/Account/v1/User/{UUID}";

    public static final String CONTENT_TYPE = "application/json";
}

