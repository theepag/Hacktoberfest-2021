const factorial = number => {
    return number < 2 ? 1 : number * factorial(number - 1);
  };

  var number = 5;
  console.log(factorial(number));
