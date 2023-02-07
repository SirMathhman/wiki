use std::env::consts::DLL_PREFIX;
use rusqlite::Connection;

fn main() {
    let connection = Connection::open("./data/data.db").unwrap();
}
