fn main() {}

#[cfg(test)]
mod tests {
    #[derive(Debug)]
    struct Node {
        title: String,
    }

    impl Clone for Node {
        fn clone(&self) -> Self {
            return Node {
                title: self.title.clone()
            }
        }
    }

    impl PartialEq for Node {
        fn eq(&self, other: &Self) -> bool {
            return self.title == other.title;
        }
    }

    #[test]
    fn test() {
        let first = Node { title: String::from("test") };
        let node = Node { title: String::from("test") };
        let second = find_any(State { node }).unwrap();
        assert_eq!(first, second);
    }

    struct State {
        node: Node,
    }

    impl State {
        fn find_any(&self) -> Option<Node> {
            return Some(self.node.clone());
        }
    }

    fn find_any(state: State) -> Option<Node> {
        return state.find_any();
    }

    #[test]
    fn test1() {}
}
