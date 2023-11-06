// Use DataBase
use your_database_name

// Create a collection
db.createCollection("movieReviews");

// Insert Data into the collection
db.movieReviews.insertMany([
  {
    title: "Inception",
    genre: ["Action", "Sci-Fi"],
    rating: 9.0,
    reviews: [
      { user: "User1", comment: "Mind-blowing plot!" },
      { user: "User2", comment: "Great visuals and storytelling." },
    ],
  },
  {
    title: "The Shawshank Redemption",
    genre: ["Drama"],
    rating: 9.7,
    reviews: [
      { user: "User3", comment: "A classic masterpiece!" },
      { user: "User4", comment: "Amazing performances." },
    ],
  },
  {
    title: "The Dark Knight",
    genre: ["Action", "Crime", "Drama"],
    rating: 9.2,
    reviews: [
      { user: "User5", comment: "Heath Ledger's Joker steals the show!" },
      { user: "User6", comment: "Intense and gripping storyline." },
    ],
  },
  {
    title: "Pulp Fiction",
    genre: ["Crime", "Drama"],
    rating: 8.9,
    reviews: [
      { user: "User7", comment: "Quentin Tarantino's masterpiece!" },
      {
        user: "User8",
        comment: "Iconic dialogues and nonlinear storytelling.",
      },
    ],
  },
  {
    title: "The Matrix",
    genre: ["Action", "Sci-Fi"],
    rating: 8.7,
    reviews: [
      { user: "User9", comment: "Revolutionary special effects!" },
      { user: "User10", comment: "A sci-fi classic." },
    ],
  },
]);
