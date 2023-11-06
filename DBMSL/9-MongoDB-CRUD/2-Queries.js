// 1. Find movies with a rating greater than 8.0:
db.movieReviews.find({ rating: { $gt: 8.0 } });

// 2. Find movies with a rating between 8.0 and 9.0:
db.movieReviews.find({ rating: { $gt: 8.0, $lt: 9.0 } });

// 3. Limit the number of rows in output
db.movieReviews.find().limit(2);

// 4. Count the number of rows in the output
db.movieReviews.find().count();

// 5. Sort the collection on the basis of rating
db.movieReviews.find().sort({ rating: 1 }); // Ascending
db.movieReviews.find().sort({ rating: -1 }); // Descending

// 6. Find movies with the genre "Sci-Fi":
db.movieReviews.find({ genre: "Sci-Fi" });

// Operations on array

// 7. $all Operator:
// Find movies with both "Action" and "Sci-Fi" genres.
db.movieReviews.find({ genre: { $all: ["Sci-Fi", "Action"] } });

// 8. Find movies with both "Action" and "Sci-Fi" genres and rating greater than 8.7
db.movieReviews.find({
  genre: { $all: ["Sci-Fi", "Action"] },
  rating: { $gt: 8.7 },
});

// 9. $slice Operator:
// Retrieve only the first two reviews for each movie.
db.movieReviews.find({}, { reviews: { $slice: 2 } });

// 10. $size Operator:
// Find movies with exactly three reviews.
db.movieReviews.find({ reviews: { $size: 3 } });

// Update Operations

// 11. Update the rating of a specific movie:
db.movieReviews.update({ title: "Inception" }, { $set: { rating: 9.2 } });

// 12. Update all movies with a rating less than 9.0 to have a minimum rating of 9.0:
db.movieReviews.updateMany({ rating: { $lt: 9.0 } }, { $set: { rating: 9.0 } });

// 13. Increase the rating of all movies by 0.5:
db.movieReviews.updateMany({}, { $inc: { rating: 0.5 } });
// To decrement just use $inc with negative sign
db.movieReviews.updateMany({}, { $inc: { rating: -0.5 } });

// 14. Add a new review to the movie "The Shawshank Redemption."
db.movieReviews.update(
  { title: "The Shawshank Redemption" },
  { $push: { reviews: { user: "User5", comment: "Brilliant plot twists!" } } }
);

// Delete Operations

// 15. Delete a single document
db.movieReviews.deleteOne({ title: "Inception" });

// 16. Deleting multiple documents on various conditions
db.movieReviews.deleteMany({
  genre: { $all: ["Sci-Fi", "Action"] },
  rating: { $gt: 8.7 },
});

// Advanced Operations: Deleting specific elements from the array of a document

// 17.Delete a specific element from the genre array in all documents where it exists.
// For example, remove the "Drama" genre:
db.movieReviews.updateMany({}, { $pull: { genre: "Drama" } });
// OR
db.movieReviews.updateMany({ genre: "Drama" }, { $pull: { genre: "Drama" } });

// 18. Delete specific embedded documents from the reviews array based on a condition.
// For example, remove all reviews by "User5":
db.movieReviews.updateMany({}, { $pull: { reviews: { user: "User5" } } });
