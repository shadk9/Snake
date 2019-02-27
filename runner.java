public class runner {

    public static void main(String[] args) {

        snakeViewGui spill = new snakeViewGui();

    }
}













//
//public void DrawBody(){
//        count = 0;
//        len = 0;
//
//        if (newEple) {
//        len++;
////            if (count == 0){
////                snakeBody.get(0).posY = snake.posY-20;
////                snakeBody.get(0).posX = snake.posX-20;
////                count = 1;
////            }
//        }
//
//        for (int i = 0; i < len; i++){
//        snakeBody.add(new snakeElement());
//        if (i < 1) {
//        snakeBody.get(i).posX = snakeBody.get(i - 1).posX;
//        snakeBody.get(i).posY = snakeBody.get(i - 1).posY;
//        System.out.println(i);
//        snakeBody.get(i).DrawElement(this.getGraphics());
//        }
//        else{
//        snakeBody.get(0).posY = snake.posY-20;
//        snakeBody.get(0).posX = snake.posX-20;
//        snakeBody.get(i).DrawElement(this.getGraphics());
//        }
//        snakeBody.get(i).DrawElement(this.getGraphics());
//        }
////        if (i == 0){
////            snakeBody.get(0).posY = snake.posY-20;
////            snakeBody.get(0).posX = snake.posX-20;
////            count = 1;
////            i++;
////        }
//    public ArrayList<snakeElement> DrawBody(){
//        int newposX = 0;
//        int newposY = 0;
//
//        if (currDir == Direction.RIGHT){
//             newposX = -20;
//             newposY = 0;
//        }
//        if (currDir == Direction.LEFT){
//            newposX = 20;
//            newposY = 0;
//        }
//        if (currDir == Direction.UP){
//            newposX = 0;
//            newposY = 20;
//        }
//        if (currDir == Direction.DOWN){
//            newposX = 0;
//            newposY = -20;
//        }
//
//        for (int i = snakeBody.size(); i == 0; i--){
//            if(i<1) {
//                snakeBody.get(i).posX = snakeBody.get(i-1).posX;
//                snakeBody.get(i).posY = snakeBody.get(i-1).posY;
//            }
//            else{
//                snakeBody.get(0).posX = snake.posX +(newposX);
//                snakeBody.get(0).posY = snake.posY +(newposY);
//            }
//        }
//        return snakeBody;
//    }
//--------------------------------------------------
//
//            if (currDir == Direction.RIGHT ){
//                newX = snake.posX +20;
//                newY = snake.posY;
//            }
//            if (currDir == Direction.LEFT){
//                newX = snake.posX -20;
//                newY = snake.posY;
//            }
//            if (currDir == Direction.UP){
//                newX = snake.posX;
//                newY = snake.posY -20;
//            }
//            if (currDir == Direction.DOWN){
//                newX = snake.posX ;
//                newY = snake.posY+20;
//            }
//            snake.posX = newX;
//            snake.posY = newY;