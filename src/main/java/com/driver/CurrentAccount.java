package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception

        super(name, balance, 5000);
        if(balance < 5000){
            throw new Exception("Insufficient Balance");
        }
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        if(isNumberValid(tradeLicenseId) == false){
            String rearrangedId = rearrange(tradeLicenseId);
            if(rearrangedId == ""){
                throw new Exception("Valid License can not be generated");
            }
            else {
                this.tradeLicenseId = rearrangedId;
            }
    }

}

public boolean isNumberValid(String tradeLicenseId){
        int n = tradeLicenseId.length();
        for(int i=0;i<n-1;i++){
            if(tradeLicenseId.charAt(i) == tradeLicenseId.charAt(i+1)){
                return false;
            }
        }
        return true;
    }

    public String rearrange(String str){
        int[] count = new int[26];
        int n = str.length();
        for(char ch : str.toCharArray()){
            count[(int)ch - (int)'A']++;
        }

        char max_ch = mostFreqChar(count);
        int max_count = count[(int)max_ch - (int)'A'];

        if(n%2 == 0){   //even case
            if(max_count > (n/2)+1){
                return "";
            }
        }
        else{       //odd case
            if(max_count > (n/2)+2){
                return "";
            }
        }

        char[] ans = new char[n];

        int index = 0;
        for(index=0;index<n;index=index+2){
            if(max_count > 0) {
                ans[index] = max_ch;
                max_count--;
            }
            else{
                break;
            }
        }

        for(int i=0;i<26;i++){
            char ch = (char)('A'+i);
            while(count[ch - 'A'] > 0){
                if(index >= n){
                    index = 1;
                }
                ans[index] = ch;
                index = index+2;
                count[ch - 'A']--;
            }
        }

        String result = String.valueOf(ans);
        return result;
    }

    public char mostFreqChar(int[] count){
        int max = 0;
        char max_ch = 0;
        int n = count.length;
        for(int i=0;i<n;i++){
            if(count[i] > max){
                max = count[i];
                max_ch = (char)('A'+i);
            }
        }
        return max_ch;
    }

}
