fn main() {}

#[cfg(test)]
mod tests {
    #[derive(Debug)]
    struct Node {
        title: String,
    }

    impl PartialEq for Node {
        fn eq(&self, other: &Self) -> bool {
            return self.title == other.title;
        }
    }

    #[test]
    fn test() {
        let first = Node { title: String::from("test") };
        let second = Node { title: String::from("test") };
        assert_eq!(first, second);
    }
}
