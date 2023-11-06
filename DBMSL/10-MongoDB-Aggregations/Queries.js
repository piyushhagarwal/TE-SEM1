// Indexing

// 1. Single Key Indexing
db.movieReviews.createIndex({ title: 1 });
// Purpose: This index is created on the title field.
// Type: Single-key indexes are used to optimize queries that filter or sort based on a single field.
// Direction: The 1 indicates ascending order. It means that MongoDB will store the index in ascending order of the title field values.

// 2. Compound Key Indexing
db.movieReviews.createIndex({ genre: 1, rating: 1 });
// Purpose: This index is a compound index created on both the genre and rating fields.
// Type: Compound indexes are used to optimize queries that filter or sort based on multiple fields.
// Direction: The 1 for both fields indicates ascending order. It means that MongoDB will store the index in ascending order of genre first and then within each genre, in ascending order of rating.

// 3. Unique Indexing
db.movieReviews.createIndex({ title: 1 }, { unique: true });
// Purpose: This index is created on the title field.
// Type: Unique indexes ensure that no two documents in the collection have the same value for the indexed field.

// Aggregations

// 1. $match : Filters document based on a specified condition
// Find movies with a rating greater than 9.0:
db.movieReviews.aggregate([{ $match: { rating: { $gt: 9.0 } } }]);

// 2. $group:
// Group movies by genre and calculate the average rating for each genre:
db.movieReviews.aggregate([
  {
    $group: {
      _id: "$genre",
      avgRating: { $avg: "$rating" },
    },
  },
]);
//Group by Genre and Count Movies in Each Genre:
db.movieReviews.aggregate([
  {
    $group: {
      _id: "$genre",
      sumRating: { $sum: 1 },
    },
  },
]);

//3. $project : Reshapes documents by including or excluding fields, creating new fields, or renaming existing ones.
// Project only the title and genre fields:
db.movieReviews.aggregate([
  {
    $project: { _id: 0, title: 1, genre: 1 },
  },
]);

//4. $sort:
// Sort movies by rating in descending order:
db.movieReviews.aggregate([{ $sort: { rating: -1 } }]);

//5. $limit and $skip:
// Limit the result to 3 documents, skipping the first 2:
db.movieReviews.aggregate([{ $skip: 2 }, { $limit: 3 }]);

db.movieReviews.aggregate([
  {
    $unwind: "$reviews",
  },
]);

// 6. $count
// Count the number of movies with a rating greater than 8.0:
db.movieReviews.aggregate([
  {
    $match: { rating: { $gt: 8.0 } },
  },
  {
    $count: "totalMovies",
  },
]);

// Distinct Genres:
// Get a list of distinct genres:
db.movieReviews.aggregate([
  { $group: { _id: "$genre" } },
  { $project: { _id: 0, genre: "$_id" } },
]);
