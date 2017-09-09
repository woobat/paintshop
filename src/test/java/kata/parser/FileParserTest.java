package kata.parser;

import com.gondor.kata.model.Problem;
import com.gondor.kata.parser.FileParser;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Created by coding on 24/08/2017.
 */
public class FileParserTest {

    @Test
    public void parseValidInput1() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("valid_problem_01.txt").getFile());

        FileParser fileParser = new FileParser();

        Problem problem = fileParser.parse(file);

        assertEquals(3, problem.totalCustomers());
    }
}