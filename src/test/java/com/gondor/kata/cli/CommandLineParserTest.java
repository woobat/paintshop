package com.gondor.kata.cli;

import com.gondor.kata.cli.exception.InvalidCommandLineArgumentsException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by coding on 17/09/2017.
 */
public class CommandLineParserTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void parse() throws IOException {
        File createdFile = folder.newFile("paintShopProblem.txt");
        String[] args = new String[]{
                "--file_path",
                createdFile.getAbsolutePath(),
                "--strategy",
                "NAIVE_SEARCH"
        };

        PaintShopOptions options = CommandLineParser.parse(args);
        assertEquals(createdFile.getAbsolutePath(), options.filePath);
        assertEquals("NAIVE_SEARCH", options.strategy);
    }

    @Test
    public void canHandleUnknownStrategy() throws IOException {
        File createdFile = folder.newFile("paintShopProblem.txt");
        String[] args = new String[]{
                "--file_path",
                createdFile.getAbsolutePath(),
                "--strategy",
                "NOT_YET_IMPLEMENTED_AWESOME_STRATEGY"
        };

        ex.expect(InvalidCommandLineArgumentsException.class);
        PaintShopOptions options = CommandLineParser.parse(args);
    }

}