import { Box, Container, Paper, Typography } from "@suid/material";

export default function () {
  return (
    <Paper
      elevation={1}
      sx={{
        padding: "10px",
      }}
    >
      <Typography variant="h4">Canvas</Typography>
      <Container>
        <Box width={"500px"} height={"500px"}>
          <span>
            test
          </span>
        </Box>
      </Container>
    </Paper>
  );
}
