package com.practicalunittesting.ppl

import com.google.gson.Gson

class Main {

	public static void main(String[] args) {
		Gson gson = new Gson();
        println gson.toJson([1, 2, 3, 4]);
    }
}

