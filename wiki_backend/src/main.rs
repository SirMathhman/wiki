use std::env::consts::DLL_PREFIX;

use diesel::{Connection, SqliteConnection};

fn main() {
    SqliteConnection::establish("./data/data.db").unwrap();

}
