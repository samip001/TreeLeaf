
class Task1{
	public static void main(String[] args) {
		
		System.out.println(replaceCharacterToNextCharacter("hellosamip"));
	}

	public static String replaceCharacterToNextCharacter(String text){
		if(text == null) throw new IllegalArgumentException("Null Value not Acceptable");

		char[] chars = text.toCharArray();
        
		StringBuilder sb = new StringBuilder();

        for(Character cha: chars){
            //for "Z" Upper case
            if(cha == 90) {
                cha = 65;
                sb.append(Character.toString(cha));
            }else if(cha == 122 ) { // for "z" Lower case
                cha = 97;
                sb.append(Character.toString(cha));

            }else if(!Character.isLetter(cha)){ // if char is other than alphabet
                continue;
            }
            else{
                cha = (char) (cha+1);
                sb.append(Character.toString(cha));
            }
        }

        return sb.toString();

	}
}