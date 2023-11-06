// Create Map function
var mapFun = function () {
  emit(this.custId, this.price);
};

// Create Reduce function
var reduceFun = function (keyCustId, valuesPrices) {
  return Array.sum(valuesPrices);
};

// Create mapReduce function
db.Orders.mapReduce(mapFun, reduceFun, { out: "Result1" });

// Display Result
db.Result.find();

// Map function 1
var mapFun1 = function () {
  emit(this.custId, this.price);
};

// Reduce function 1
var reduceFun1 = function (keyCustId, valuesPrices) {
  return Array.avg(valuesPrices);
};

// Create mapReduce function with query
db.Orders.mapReduce(mapFun1, reduceFun1, {
  query: { custId: { $gt: 102 } },
  out: "Result2",
});

// Display Result
db.Result2.find();

// Map function 2
var mapFun2 = function () {
  emit(this.custId, this.price);
};

// Reduce function 2
var reduceFun2 = function (keyCustId, valuesPrices) {
  return Array.sum(valuesPrices);
};

// mapReduce with query
db.Orders.mapReduce(mapFun2, reduceFun2, {
  query: { custId: { $gt: 102 } },
  out: "Result3",
});

// Display Result
db.Result.find();
