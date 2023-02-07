use std::env::consts::DLL_PREFIX;

use diesel::{Connection, SqliteConnection};
use diesel::pg::PgConnection;

fn main() {
    SqliteConnection::establish("./data/data.db").unwrap();
}
