public class constant {
    static public final int PROG_NAME_LENGTH = 128;
    static public final int COMMENT_LENGTH = 2048;
    static public final int COREWAR_EXEC_MAGIC = 0xea83f3;

    static public final int IND_SIZE = 2;
    static public final int REG_SIZE = 4;
    static public final int DIR_SIZE = REG_SIZE;

    static public final int REG_CODE = 1;
    static public final int DIR_CODE = 2;
    static public final int IND_CODE = 3;

    static public final int MAX_ARGS_NUMBER = 4;
    static public final int MAX_PLAYERS = 4;
    static public final int MEM_SIZE = (4 * 1024);
    static public final int IDX_MOD = (MEM_SIZE / 8);
    static public final int CHAMP_MAX_SIZE = (MEM_SIZE / 6);


    static public final char DIRECT_CHAR = '%';
    static public final char SEPARATOR_CHAR = ',';


    static public final String NAME_CMD_STRING = ".name";
    static public final String COMMENT_CMD_STRING = ".comment";


    static public final int T_REG = 1;
    static public final int T_DIR = 2;
    static public final int T_IND = 3;
    static public final int T_LAB = 8;

//    static public final int T_REG			1
//            static public final int T_DIR			2
//            static public final int T_IND			4
//            static public final int T_REG_DIR		(T_REG + T_DIR)
//static public final int T_REG_IND		(T_REG + T_IND)
//static public final int T_DIR_IND		(T_DIR + T_IND)
//static public final int T_REG_DIR_IND	(T_REG + T_DIR + T_IND)


    static public final int   CMD_LIVE = 1;
    static public final int    CMD_LD=2;
    static public final int     CMD_ST=3;
    static public final int     CMD_ADD=4;
    static public final int    CMD_SUB=5;
    static public final int    CMD_AND=6;
    static public final int    CMD_OR=7;
    static public final int    CMD_XOR=8;
    static public final int    CMD_ZJMP=9;
    static public final int     CMD_LDI=10;
    static public final int     CMD_STI=11;
    static public final int     CMD_FORK=12;
    static public final int     CMD_LLD=13;
    static public final int     CMD_LLDI = 14;
    static public final int     CMD_LFORK = 15;
    static public final int     CMD_AFF = 16;

    static public final int     ARG_COUNT = 1;
    static public final int     HAS_ARG_BYTE = 2;



//    static public final int	arg_tab[CMD_LFORK][10] =
//    {
//        {0, ARG_COUNT, HAS_ARG_BYTE},
//        {CMD_LIVE, 1, 0, 0, 0, 0},
//        {CMD_LD, 2, 1, T_DIR_IND, T_REG, 0},
//        {CMD_ST, 2, 1, T_REG, T_REG_IND, 0},
//        {CMD_ADD, 3, 1, T_REG, T_REG, T_REG},
//        {CMD_SUB, 3, 1, T_REG, T_REG, T_REG},
//        {CMD_AND, 3, 1, T_REG_DIR_IND, T_REG_DIR_IND, T_REG},
//        {CMD_OR, 3, 1, T_REG_DIR_IND, T_REG_DIR_IND, T_REG},
//        {CMD_XOR, 3, 1, T_REG_DIR_IND, T_REG_DIR_IND, T_REG},
//        {CMD_ZJMP, 1, 0, T_DIR, 0, 0},
//        {CMD_LDI, 3, 1, T_REG_DIR_IND, T_REG_DIR, T_REG},
//        {CMD_STI, 3, 1, T_REG, T_REG_DIR_IND, T_REG_DIR},
//        {CMD_FORK, 1, 0, T_DIR, 0, 0},
//        {CMD_LLD, 2, 1, T_DIR_IND, T_REG, 0},
//        {CMD_LLDI, 3, 1, T_REG_DIR_IND, T_REG_DIR, T_REG},
//        {CMD_LFORK, 1, 0, T_DIR, 0, 0},
//        {CMD_AFF, 1, 1, T_REG, 0, 0}
//    };


    public static int argCount(int cmd) {
        switch (cmd) {
            case CMD_LIVE : return (1);
            case CMD_LD :
            case CMD_ST : return (2);
            case CMD_ADD :
            case CMD_SUB :
            case CMD_AND :
            case CMD_OR :
            case CMD_XOR : return (3);
            case CMD_ZJMP : return (1);
            case CMD_LDI :
            case CMD_STI : return (3);
            case CMD_FORK : return (1);
            case CMD_LLD : return (2);
            case CMD_LLDI : return (3);
            case CMD_LFORK :
            case CMD_AFF : return (1);
        }
        return 0;
    }

    public static boolean hasByte(int cmd) {
        switch (cmd) {
            case CMD_LIVE : return (false);
            case CMD_LD : return (true);
            case CMD_ST : return (true);
            case CMD_ADD : return (true);
            case CMD_SUB : return (true);
            case CMD_AND : return (true);
            case CMD_OR : return (true);
            case CMD_XOR : return (true);
            case CMD_ZJMP : return (false);
            case CMD_LDI : return (true);
            case CMD_STI : return (true);
            case CMD_FORK : return (false);
            case CMD_LLD : return (true);
            case CMD_LLDI : return (true);
            case CMD_LFORK : return (false);
            case CMD_AFF : return (true);
        }
        return (false);
    }

    public static int getDirSize(int cmd) {
        switch (cmd) {
            case CMD_LIVE :
            case CMD_LD :
            case CMD_ST :
            case CMD_ADD :
            case CMD_SUB :
            case CMD_AND :
            case CMD_OR :
            case CMD_XOR : return (4);
            case CMD_ZJMP :
            case CMD_LDI :
            case CMD_STI :
            case CMD_FORK : return (2);
            case CMD_LLD : return (4);
            case CMD_LLDI : return (2);
            case CMD_LFORK :return (2);
            case CMD_AFF : return (4);
        }
        return 0;
    }

    public static String getNameCmd(int cmd) {
        switch (cmd) {
            case CMD_LIVE : return("live");
            case CMD_LD : return ("ld");
            case CMD_ST : return ("st");
            case CMD_ADD : return ("add");
            case CMD_SUB : return ("sub");
            case CMD_AND : return ("and");
            case CMD_OR : return ("or");
            case CMD_XOR : return ("xor");
            case CMD_ZJMP : return ("zjmp");
            case CMD_LDI : return ("ldi");
            case CMD_STI : return ("sti");
            case CMD_FORK : return ("fork");
            case CMD_LLD : return ("lld");
            case CMD_LLDI : return ("lldi");
            case CMD_LFORK : return ("lfork");
            case CMD_AFF : return ("aff");
        }
        return ("");
    }
}
