package com.app.toy.robot;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.app.toy.robot.common.DirectionEnum;
import com.app.toy.robot.exception.InvalidCommandException;
import com.app.toy.robot.exception.InvalidDirectionException;
import com.app.toy.robot.exception.InvalidPositionException;
import org.junit.Assert;
import org.junit.Test;

public class ToyRobotImplTest {

    private Table table = new Table();
    private ToyRobotImpl toyRobotImpl = new ToyRobotImpl(table);


    @Test
    public void testPlace() throws Exception {
        toyRobotImpl.place(3,4, DirectionEnum.EAST);
        Assert.assertEquals(DirectionEnum.EAST.getValue(), toyRobotImpl.getDirectionEnum().getValue());
        Assert.assertEquals(3, toyRobotImpl.getPosX());
        Assert.assertEquals(4, toyRobotImpl.getPosY());
    }

    @Test
    public void testInvalidPosition() {
        assertThatThrownBy(() -> {
            toyRobotImpl.place(8,7, DirectionEnum.EAST);
        }).isInstanceOf(InvalidPositionException.class)
                .withFailMessage("X, Y should not be less than 0.");

        Assert.assertNull(toyRobotImpl.getDirectionEnum());
    }

    @Test
    public void testInvalidNegativePosition(){
        assertThatThrownBy(() -> {
            toyRobotImpl.place(-1,0, DirectionEnum.EAST);
        }).isInstanceOf(InvalidPositionException.class)
        .withFailMessage("X, Y should not be less than 0.");

        //Direction should not be set if position is invalid
        Assert.assertNull(toyRobotImpl.getDirectionEnum());
    }


    @Test
    public void testNullDirection() {
        assertThatThrownBy(() -> {
            toyRobotImpl.place(3, 4, null);
        }).isInstanceOf(InvalidDirectionException.class)
                .withFailMessage("Direction must be specified.");

        //Direction should not be set if position is invalid
        Assert.assertNull(toyRobotImpl.getDirectionEnum());
    }


    @Test
    public void testInvalidCommand() {
        assertThatThrownBy(() -> {
            toyRobotImpl.runCommand("PLACE 0,0,NORTHEAST");
        }).isInstanceOf(InvalidCommandException.class);

        //Direction should not be set if position is invalid
        Assert.assertNull(toyRobotImpl.getDirectionEnum());
    }

    @Test
    public void testMove() throws Exception {
        toyRobotImpl.place(3,4, DirectionEnum.EAST);
        Assert.assertEquals(DirectionEnum.EAST, toyRobotImpl.getDirectionEnum());
        Assert.assertEquals(1, toyRobotImpl.getDirectionEnum().getValue());
        toyRobotImpl.move();
        Assert.assertEquals(4, toyRobotImpl.getPosX());
        Assert.assertEquals(4, toyRobotImpl.getPosY());
    }

    @Test
    public void testRotateLeft() throws Exception {
        toyRobotImpl.place(3,3, DirectionEnum.EAST);
        toyRobotImpl.rotateLeft();
        Assert.assertEquals(DirectionEnum.NORTH.getValue(), toyRobotImpl.getDirectionEnum().getValue());
        toyRobotImpl.rotateLeft();
        Assert.assertEquals(DirectionEnum.WEST.getValue(), toyRobotImpl.getDirectionEnum().getValue());
        toyRobotImpl.rotateLeft();
        Assert.assertEquals(DirectionEnum.SOUTH.getValue(), toyRobotImpl.getDirectionEnum().getValue());
        toyRobotImpl.rotateLeft();
        Assert.assertEquals(DirectionEnum.EAST.getValue(), toyRobotImpl.getDirectionEnum().getValue());

    }

    @Test
    public void testRotateRight() throws Exception {
        toyRobotImpl.place(3,3, DirectionEnum.EAST);
        toyRobotImpl.rotateRight();
        Assert.assertEquals(DirectionEnum.SOUTH.getValue(), toyRobotImpl.getDirectionEnum().getValue());
        toyRobotImpl.rotateRight();
        Assert.assertEquals(DirectionEnum.WEST.getValue(), toyRobotImpl.getDirectionEnum().getValue());
        toyRobotImpl.rotateRight();
        Assert.assertEquals(DirectionEnum.NORTH.getValue(), toyRobotImpl.getDirectionEnum().getValue());
        toyRobotImpl.rotateRight();
        Assert.assertEquals(DirectionEnum.EAST.getValue(), toyRobotImpl.getDirectionEnum().getValue());

    }

    @Test
    public void testShowReport() throws Exception {
        toyRobotImpl.place(0,0, DirectionEnum.NORTH);
        toyRobotImpl.move();
        Assert.assertEquals(0, toyRobotImpl.getPosX());
        Assert.assertEquals(1, toyRobotImpl.getPosY());
        Assert.assertEquals(DirectionEnum.NORTH.getValue(), toyRobotImpl.getDirectionEnum().getValue());
        toyRobotImpl.showReport();

        toyRobotImpl.place(0,0, DirectionEnum.NORTH);
        toyRobotImpl.rotateLeft();
        Assert.assertEquals(0, toyRobotImpl.getPosX());
        Assert.assertEquals(0, toyRobotImpl.getPosY());
        Assert.assertEquals(DirectionEnum.WEST.getValue(), toyRobotImpl.getDirectionEnum().getValue());
        toyRobotImpl.showReport();

        toyRobotImpl.place(1,2, DirectionEnum.EAST);
        toyRobotImpl.move();
        toyRobotImpl.move();
        toyRobotImpl.rotateLeft();
        toyRobotImpl.move();
        Assert.assertEquals(3, toyRobotImpl.getPosX());
        Assert.assertEquals(3, toyRobotImpl.getPosY());
        Assert.assertEquals(DirectionEnum.NORTH.getValue(), toyRobotImpl.getDirectionEnum().getValue());
        toyRobotImpl.showReport();
    }

}
