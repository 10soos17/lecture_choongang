//=====================9_11=====================
function change(number) {
  let inch = (Number(number) / 254) * 100;
  console.log(inch);
  ///254*100;
  let feet = inch / 12;
  console.log(inch, feet);
  return inch, feet;
}
//=====================9_12=====================
function cal_volume(radius, height) {
  let volume = Number(radius) * 2 * 3.141592 * Number(height);
  console.log(volume);
  return volume;
}
//=====================9_13=====================
function seperate_number(number) {
  let first = parseInt(number / 1000);
  let second = parseInt((number % 1000) / 100);
  let third = parseInt((number % 100) / 10);
  let fourth = parseInt(number % 10);

  console.log(first, second, third, fourth);
}
//=====================9_14=====================
function year(number) {
  let yearDic = {
    0: "원숭이띠",
    1: "닭띠",
    2: "개띠",
    3: "돼지띠",
    4: "쥐띠",
    5: "소띠",
    6: "범띠",
    7: "토끼띠",
    8: "용띠",
    9: "뱀띠",
    10: "말띠",
    11: "양띠",
  };
  let year = yearDic[number % 12];

  console.log(year);
  return year;
}

function year2() {
  var ddi = prompt("년도를 입력하세요.");
  var result = ddi % 12;

  switch (result) {
    case 0:
      document.write("원숭이띠");
      break;

    case 1:
      document.write("닭띠");
      break;
  }
}
function year3() {
  var ddi = [
    "원숭이",
    "닭",
    "개",
    "돼지",
    "쥐",
    "소",
    "범",
    "토끼",
    "용",
    "뱀",
    "말",
    "양",
  ];
  var input = parseInt(prompt("태어난 연도를 입력하세요."));
  document.write(ddi[input % 12]);
}
//=====================9_15=====================
/*
var output = '';
for (var i = 0; i < 10; i++) {//row
  for (var j = 0; j < i+1; j++){//col
    output += "*";
  }
  output += '\n';
}
alert(output);


for (var i = 0; i < 10; i++){
  for (var j = 0; j < 10 - i; i++){
    output += "*"
  }
  output +="\n"
}
alert(output);

function factorial(num){
  var output=1;
  for(var i=1; i<num;i++){
    output*=i;
  }
  return output;
}

*/
//================증가
function tree0() {
  let star = "";
  for (let i = 0; i < 10; i++) {
    for (let j = 9 - i; j < 10; j++) {
      star += "*";
    }
    console.log(star);
    star = "";
  }
}

//===============감소
function tree1() {
  //===1th
  for (var i = 0; i < 10; i++) {
    for (var j = 0; j < 10 - i; j++) {
      document.write("*");
    }
    document.write("<br>");
  }
  //====2th
  for (var i = 10; i > 0; i--) {
    for (var j = i; j > 0; j--) {
      document.write("*");
    }
    document.write("<br>");
  }
}

//===============3중 test
function tree2() {
  for (let i = 1; i <= 10; i++) {
    for (let j = 1; j <= i; j++) {
      for (let k = 1; k <= j; k++) {
        document.write("*");
      }
      document.write("<br>");
    }
    document.write("<br>");
  }
}
function tree3() {
  for (var i = 0; i < 10; i++) {
    for (var j = 9 - i; j < 10; j++) {
      for (var k = j; k < 10; k++) {
        document.write("*");
        //document.write(`3_${i}${j}${k}`, "\t");
      }

      document.write("<br>");
      //document.write(`2_${i}${j}${k}`, "\t");
    }

    document.write("<br>");
    //document.write(`1_${i}${j}${k}`, "\t");
  }
}

//=====================9_16=====================
function factorial(num) {
  let number = num;
  let res = num;

  while (number != 1) {
    number -= 1;
    res *= number;
  }
  console.log(res);
  return res;
}

output = "";

function square(number) {
  number = 5;

  for (var i = 0; i < number; i++) {
    for (var j = 0; j < number; j++) {
      output += "*";
      //document.write("*");
    }
    output += "\n";
    // document.write("<br>");
  }
  console.log(output);
  //alert(output);
}

function treeplus(number) {
  number = 5;
  for (var i = 0; i < number; i++) {
    for (var j = 0; j < i + 1; j++) {
      output += "*";
      //document.write("*");
    }
    output += "\n";
    //document.write("<br>");
  }
  console.log(output);
}
function trianglePlusSpace(number) {
  number = 5;
  for (var i = 0; i < number; i++) {
    for (var j = 0; j < number; j++) {
      if (j < number - i - 1) {
        output += " ";
      } else {
        output += "*";
      }
    }
    output += "\n";
  }
  console.log(output);
}

function treeminus(number) {
  number = 5;
  for (var i = 0; i < number; i++) {
    for (var j = 0; j < number - i; j++) {
      output += "*";
      document.write("*");
    }
    output += "\n";
    document.write("<br>");
  }
  console.log(output);
}

function triangleMinusSpace(number) {
  number = 5;
  for (var i = 0; i < number; i++) {
    for (var j = 0; j < number; j++) {
      if (j < i) {
        output += " ";
      } else {
        output += "*";
      }
    }
    output += "\n";
  }
  console.log(output);
}

function e_trianglePlus(height) {
  height = 5;
  width = height * 2 + 1;

  for (var i = 1; i < height + 1; i++) {
    occupy_s = i * 2 - 1; //******** */
    empty_s = width - occupy_s;
    left_p = empty_s / 2;
    right_p = empty_s / 2 + occupy_s - 1;

    for (var j = 0; j < width; j++) {
      if (j < left_p || j > right_p) {
        output += "_";
      } else {
        output += "*";
      }
    }
    output += "\n";
  }
  console.log(output);
}

function e_triangleMinus(height) {
  height = 5;
  width = height * 2 + 1;

  for (var i = 1; i < height + 1; i++) {
    occupy_s = width - i * 2 + 1; //********** */
    empty_s = width - occupy_s;
    left_p = empty_s / 2;
    right_p = empty_s / 2 + occupy_s - 1;

    for (var j = 0; j < width; j++) {
      if (j < left_p || j > right_p) {
        output += "_";
      } else {
        output += "*";
      }
    }
    output += "\n";
  }
  console.log(output);
}
